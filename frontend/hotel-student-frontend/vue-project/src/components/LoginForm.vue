<template>
  <div class="login-form">
    <h2>登录系统</h2>

    <p>测试代码同步</p>
    <p>新增修改</p>
    <div>
      <label>用户名：</label>
      <input v-model="username" type="text" />
    </div>

    <div>
      <label>密码：</label>
      <input v-model="password" type="password" />
    </div>

    <!-- 登录按钮 -->
     <button @click="handleLogin">登录</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router' //导入路由勾子

// 创建响应式变量（绑定输入框）
const username = ref('')
const password = ref('')
const router = useRouter() //获取路由实例

// 登录方法
const handleLogin= async()=>{
  console.log('username', username.value);
  console.log('password', password.value);
  // 点击登录,向后端发送post请求
  try{
    const response = await axios.post('/api/login', {
      username: username.value,
      password: password.value
    })
    router.push('/student')
    console.log('✅ 登录响应：', response.data)
  } catch (error) {
    console.error('❌ 登录请求失败：', error)
  }

}
</script>

<style scoped>
.login-form {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
}

.login-form div {
  margin-bottom: 12px;
}
</style>
