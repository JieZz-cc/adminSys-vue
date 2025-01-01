import router from "@/router/index"
import { useRouter } from "vue-router"

const modules = import.meta.glob('@/views/**/*.vue')

/**
 * @description 递归菜单，将其扁平化放在一个array里
 * @param {*} menuList 菜单列表
 * @return {*}  Array
 */
export function getFlatMenuList(menuList) {

  let newMenuList = JSON.parse(JSON.stringify(menuList))
  return newMenuList.flatMap(item => [
    item,
    ...(item.children? getFlatMenuList(item.children) : [])
  ])

}

/**
 * @description 左侧菜单列表
 * @param {*} menuList 菜单列表
 * @return {*}  Array
 */
export function getShowMenuList(menuList) {
  let newMenuList = JSON.parse(JSON.stringify(menuList))
  return newMenuList.filter(item => {
    item.children?.length && (item.children = getShowMenuList(item.children))
    return item.isEnable
  }) 
}


/**
 * @description 使用递归找出所有面包屑存储到 pinia/vuex 中
 * @param {Array} menuList 菜单列表
 * @param {Array} parent 父级菜单
 * @param {Object} result 处理后的结果
 * @returns {Object}
 */
export function getAllBreadcrumbList(menuList = [], parent = [], result = {}) {
  // console.log(menuList, '--');
  
  for (let item of menuList) {
    result[item.path] = [...parent, item] // 本路径：父级对象+自己  父级递归
    if (item.children) getAllBreadcrumbList(item.children, result[item.path], result)
  }
  return result
}

export function  addNewTab(menuId, path, title,  isKeepAlive, meta = {}) {
  const use_router = useRouter()
  // path 为views文件夹下
  let item  = {
    name: menuId,
    path: path,
    title: title,
    children: [],
    meta: {
      isAffix: false,
      isEnable: true,
      isKeepAlive: isKeepAlive,
      isLink: false,
      title: title
    },
    query: meta,
    component: modules['/src/views' + path + '.vue']
  }
  router.addRoute('layout', item)
  
  router.push(path, meta)
}

export function findNodeById(tree = [], id) {
  for (const node of tree) {
    if (node.id === id) {
      return node
    }
    if (node.children && node.children.length > 0) {
      const foundNode = findNodeById(node.children, id)
      if (foundNode) {
        return foundNode
      }
    }
  }
  return null
}