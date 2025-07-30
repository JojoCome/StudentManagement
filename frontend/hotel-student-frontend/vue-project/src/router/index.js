import { createRouter,createWebHistory } from "vue-router";
import LoginForm from "@/components/LoginForm.vue";
import StudentList from "@/components/StudentList.vue";

//路由规则:URL 地址对应的组件。这是路由表:像字典一样记住所有组件,并分配地址
const routes = [
  {  path: '/', component: LoginForm }, // 默认访问 / 路径时，显示 LoginForm 组件
  {  path: '/login', component: LoginForm },
  {  path: '/student', component: StudentList },
]

//  创建路由实例
const router = createRouter({
  history: createWebHistory(), // 使用HTML5 history模式
  routes,
})

export default router