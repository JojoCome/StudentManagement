<template>
  <h2>学生列表</h2>
  <table border="1">
    <thead>
      <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>国籍</th>
        <th>入职日期</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="student in students" :key="student.id">
        <td>{{ student.id }}</td>
        <td>{{ student.name }}</td>
        <td>{{ student.gender }}</td>
        <td>{{ student.nationality }}</td>
        <td>{{ student.joinDate }}</td>
        <td>
          <button @click="editStudent(student)">编辑</button>
          <button @click="handleDelete(student.id)">删除</button>
        </td>
      </tr>
    </tbody>
  </table>

  <!-- 添加学生表单 -->
  <div style="margin-bottom: 20px;">
    <h3>添加新学生</h3>
    <form @submit.prevent="addStudent">
      <input v-model="newStudent.id" placeholder="编号" required />
      <input v-model="newStudent.name" placeholder="姓名" required />
      <input v-model="newStudent.gender" placeholder="性别" required />
      <input v-model="newStudent.nationality" placeholder="国籍" required />
      <input v-model="newStudent.joinDate" placeholder="入职日期(YYYY-MM-DD)" required />
      <!-- <button type="submit">添加</button> -->
       <br>
      <button v-if="!isEditMode">添加</button>
      <button v-else @click="updateStudent" type="button">更新</button>
      <button v-if="isEditMode" @click="cancelEdit" type="button">取消</button>

    </form>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios';
const isEditMode = ref(false);
const editingId = ref(null);


const students = ref([
  // 这里的数据已经加载完毕，直接使用即可
])//  定义一个响应式数据
const newStudent = ref({
  id: '',
  name: '',
  gender: '',
  nationality: '',
  joinDate: ''
})

//  定义addStudent方法 添加学生
const addStudent = async () => {
  await axios.post('/api/students', newStudent.value)
  //添加成功后,情况输入框
  newStudent.value = {
    id: '',
    name: '',
    gender: '',
    nationality: '',
    joinDate: ''
  };
  fetchStudent();
}
//  在页面加载时，调用接口获取数据
onMounted(() => {
  fetchStudent()
})

// 删除学生
const handleDelete = async (id) => {
  const confirmDelete = confirm(`确认要删除编号为${id}的学员吗？`);
  if (!confirmDelete) {
    return;
  }
  try {
    await axios.delete(`/api/students/${id}`);
    fetchStudent();
  } catch (error) {
    console.log('删除失败', error);
    alert('删除失败');
  }
}
//修改学生信息
const updateStudent = async () => { 
  await axios.put(`/api/update/${newStudent.value.id}`, newStudent.value);
  fetchStudent();
  cancelEdit();
}
const editStudent = (student) => {
  newStudent.value = { ...student };
  editingId.value = student.id; // 记录编辑的id
  isEditMode.value = true;//  进入编辑模式
}
// 取消按钮的功能,就是把响应数据都复原
const cancelEdit = () => {
  newStudent.value = {};
  editingId.value = null;
  isEditMode.value = false;
}
//页面更新
const fetchStudent = async () => {
  try {
    const res = await axios.get('/api/students');
    students.value = res.data;
  } catch (error) {
    console.log(error);
  }
}
</script>
<style scoped></style>