
<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
    <el-form-item label="姓名" prop="name">
      <el-input v-model="ruleForm.name" style="width: 45%"></el-input>
    </el-form-item>
    <el-form-item label="年级学院专业" prop="collegeMajor">
    <el-input v-model="ruleForm.collegeMajor" style="width: 45%"></el-input>
  </el-form-item>
    <el-form-item label="学号" prop="studentNumber">
      <el-input v-model="ruleForm.studentNumber" style="width: 45%"></el-input>
    </el-form-item>
    <el-form-item label="意向部门" prop="department">
      <el-radio-group v-model="ruleForm.department">
        <el-radio label="软件部"></el-radio>
        <el-radio label="运维部"></el-radio>
        <el-radio label="其他部"></el-radio>
      </el-radio-group>

    </el-form-item>
    <el-form-item label="自我介绍" prop="introduction">
      <el-input type="textarea" v-model="ruleForm.introduction" style="width: 45%"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="save">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      ruleForm: {
        id:'',
        name: '',
        collegeMajor: '',
        studentNumber: '',
        department: '',
        introduction: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        collegeMajor: [
      { required: true, message: '请输入年级学院专业', trigger: 'blur' },
      { min: 4, message: '不少于4个字符', trigger: 'blur' }
    ],
        studentNumber: [
          { required: true, message: '请输入学号', trigger: 'blur' },
          { min: 10, max: 11,message: '请输入正确的学号', 个trigger: 'blur' }
        ],

        department: [
          { required: true, message: '请选择活动资源', trigger: 'change' }
        ],
        introduction: [
          { required: true, message: '请填写自我介绍', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    save(){
      request.post("/api/user", this.ruleForm).then(res => {
        console.log(res)
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "新增成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }

        this.load() // 刷新表格的数据
        this.dialogVisible = false  // 关闭弹窗
      })
      },
    resetForm(formName){
      this.$refs[formName].resetFields();
    }

}}
</script>

<style>

</style>
