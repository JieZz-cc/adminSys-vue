<template>
  <div class="dashboard">
    <div class="top">
      <div class="top-left">
        <div>
          <span style="font-size: 24px;font-weight: bolder;margin-right: 10px;">欢迎回来~</span>
          <span class="text-color">{{ currUser.username }}</span>
        </div>
        <div style="margin-top: 8px;">
          <span style="color: #999;margin-right: 15px;">{{ currUser.deptName }}</span>
          <span style="color: #999;">{{ currUser.roleName }}</span>
        </div>
      </div>
      <div class="top-right">
        <p>{{ dateObj.yinLi }}</p>
        <p>
          <span style="margin-right: 16px;">{{ dateObj.yangLi }}</span>
          <span>{{ dateObj.weeDay }}</span>
        </p>

      </div>
    </div>
    <div class="middle">
      <el-collapse v-model="activeNames" @change="collapseChange">
        <el-collapse-item title="系统介绍" name="1">
          <p class="fz-16">本系统是基于用户角色的权限控制系统，使用超级管理员角色可开启系统管理功能，通过部门管理、用户管理、角色管理等功能
            实现用户角色的权限控制。
          </p>
        </el-collapse-item>
        <el-collapse-item title="技术方案" name="2">
          <p class="fz-16">
            系统采用前后分离的设计方案，前端基于Vue3 + Js，后端基于SpringBoot3 + Java17，前后交互采用axios技术。
          </p>
        </el-collapse-item>
        <el-collapse-item title="开源协议" name="3">
          <p class="fz-16">基于MIT开源协议，免费全开源且可商用，无版权问题。</p>
        </el-collapse-item>
        <el-collapse-item title="后续优化" name="4">
          <p class="fz-16">
            1、登录增加验证码拦截；<br>
            2、将系统拆分成几个微服务；
          </p>
        </el-collapse-item>
      </el-collapse>
    </div>
    <div class="bottom">
      <div class="yilai">
          <el-card class="card">
            <template #header>
              <div class="card-header">
                <span>前端依赖</span>
              </div>
            </template>
            <div v-for="(item, index) in frontCardData" :key="index" class="grid-box">
              <el-link :underline="false" type="primary" :href="item.href" target="_blank">
                {{ item.name }}
              </el-link>
            </div>
          </el-card>
          <el-card class="card">
            <template #header>
              <div class="card-header">
                <span>后端依赖</span>
              </div>
            </template>
            <div v-for="(item, index) in backCardData" :key="index" class="grid-box">
              <el-link :underline="false" type="primary" :href="item.href" target="_blank">
                {{ item.name }}
              </el-link>
            </div>
          </el-card>
      </div>
      <div class="dizhi mg-t-15">
        <el-card class="card" @click="toGitee">
          <div>
            <img src="https://gitee.com/static/images/logo.svg?t=158106664" alt="" style="height: 40px" v-if="globalStore.isDark">
            <img src="https://gitee.com/static/images/logo-black.svg?t=158106664" alt="" style="height: 40px" v-else>
          </div>
          <p style="color: #999;padding-left: 10px;">ccc / AdminSys</p>
        </el-card>
        <el-card class="card" @click="toGithub">
          <div>
            <img src="@/assets/images/github-mark-white.svg" alt="" style="height: 40px" v-if="globalStore.isDark">
            <img src="@/assets/images/github-mark.svg" alt="" style="height: 40px" v-else>
            <span>Github</span>
          </div>
          <p style="color: #999;padding-left: 10px;">JieZz-cc / AdminSys</p>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, onMounted, reactive, ref } from 'vue'
import { userStore } from '@/stores/userStore'
import {useGlobalStore} from '@/stores/modules/global'
const useUserStore = userStore()
const globalStore = useGlobalStore()


const frontCardData = [
  {href: 'https://cn.vuejs.org/guide/introduction', name: 'Vue'},
  {href: 'https://element-plus.org', name: 'Element-plus'},
  {href: 'https://pinia.vuejs.org/zh/introduction.html', name: 'Pinia'},
  {href: 'https://www.axios-http.cn/', name: 'Axios'},
  {href: 'https://echarts.apache.org/zh/index.html', name: 'Echarts'}
]
const backCardData = [
  {href: 'https://spring.io/projects/spring-boot', name: 'SpringBoot'},
  {href: 'https://mybatis.org/mybatis-3/zh_CN/index.html', name: 'Mybatis'},
  {href: 'https://redis.io/', name: 'Redis'},
  {href: 'https://projectlombok.org/', name: 'Lombok'}
]

const toGitee = () => {
  window.open('https://gitee.com/dashboard/projects', '_blank')
}

const toGithub = () => {
  window.open('https://github.com/JieZz-cc?tab=repositories', '_blank')
}

const currUser = computed(() => useUserStore.userInfo)
const dateObj = reactive({
  yangLi: '',
  yinLi:'',
  weeDay: ''
})

const formatDate = () => {
  const date = new Date()
  const yangLi = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
  const yinLi = date.toLocaleDateString('ja-JP-u-ca-chinese')
  const weekDay = '星期' + date.getDay()
  const d = yinLi.split('-')[0] + '年' + yinLi.split('-')[1] + '月' + yinLi.split('-')[2] + '日'
  dateObj.yangLi = yangLi
  dateObj.yinLi = d
  dateObj.weeDay = weekDay
}

const activeNames = ref([])
const collapseChange = (val) => {
  // console.log(val);
}


onMounted(() =>{
  formatDate()
})


</script>

<style lang="scss" scoped>
@import url('./index.scss');
:deep(.el-collapse) {
  width: 100%;
}
</style>