import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '@/views/AboutView.vue')
  },
  {
    path: '/explainpage',
    name: 'ExplainPage',
    //설명페이지 라우터

    component: () => import('@/views/main/explain/ExplainPage.vue')
  },
  {
    path: '/historypage',
    name: 'HistoryPage',
    //explain router

    component: () => import('@/views/main/history/HistoryPage.vue')
  },
  {
    path: '/loginpage',
    name: 'LoginPage',
    //login page router

    component: () => import('@/views/main/login/LoginPage.vue')
  },
  {
    path: '/qnapage',
    name: 'QnaPage',
    //qna router

    component: () => import('@/views/main/qna/QnaPage.vue')
  },
  {
    path: '/noticepage',
    name: 'NoticePage',
    //qna router

    component: () => import('@/views/main/notice/NoticePage.vue')
  },
  {
    path: '/teampage',
    name: 'TeamPage',
    //qna router

    component: () => import('@/views/main/team/TeamPage.vue')
  },
  {
    path: '/signuppage',
    name: 'SigunupPage',
    //explain router

    component: () => import('@/views/main/history/HistoryPage.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
