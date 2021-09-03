import { createRouter, createWebHistory } from 'vue-router'
import Search from '../views/Search.vue'
import Registration from '../views/Registration.vue'

const routes = [
  {
    path: '/Registration',
    name: 'Registration',
    component: Registration
  },
  {
    path:'/Search',
    name:'Search',
    component: Search


  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
