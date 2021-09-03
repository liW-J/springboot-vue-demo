<template>
  <div style="padding: 10px" class="home">
    <div style="margin: 10px 0">
     <el-button type="primary" @click="add">新增报名</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin: 5px" @click="load">查询</el-button>


    </div>
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%">
      <el-table-column
          prop="id"
          label="ID"
      sortable>
      </el-table-column>
      <el-table-column
          prop="name"
          label="姓名">
      </el-table-column>
      <el-table-column
        prop="studentNumber"
        label="学号">
    </el-table-column>
      <el-table-column
          prop="collegeMajor"
          label="年级学院专业">
      </el-table-column>
      <el-table-column
        prop="department"
        label="意向部门">
    </el-table-column>
      <el-table-column
          prop="introduction"
          label="自我介绍">
      </el-table-column>
      <el-table-column
          label="操作"
          width="100">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" type="text" >编辑</el-button>
          <el-popconfirm title="这是一段内容确定删除吗？" @confirm="handleDelete(scope.row.id)" >
            <template #reference>
              <el-button type="text">删除</el-button>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>
    <div class="block" style="margin: 10px">

      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
      <el-dialog title="提示" v-model="dialogVisible" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item  label="姓名">
            <el-input v-model="form.name" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item  label="年级学院专业">
            <el-input v-model="form.collegeMajor" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item  label="学号">
            <el-input v-model="form.studentNumber" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item  label="意向部门">
            <el-radio v-model="form.department" label="软件部">软件部</el-radio>
            <el-radio v-model="form.department" label="运维部">运维部</el-radio>
            <el-radio v-model="form.department" label="其他部">其他部</el-radio>

          </el-form-item>
          <el-form-item  label="个人简介">
            <el-input type="textarea" v-model="form.introduction" style="width: 80%"></el-input>
          </el-form-item>

        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </span>
        </template>
        </el-dialog>

    </div>





  </div>
</template>

<script>
// @ is an alias to /src


import request from "@/utils/request";

export default {
  name: 'Search',
  components: {

  },
  data(){

    return {
      radio:"未知",
      form:{},
      dialogVisible: false,
      search:'',
      currentPage:1,
      pageSize:10,
      total:0,
      tableData: [],
      loading: true,
    }

  },
  created(){
    this.load()
  },
  methods: {
    add(){
      this.dialogVisible = true
      this.form={}


    },
    load() {
      this.loading = true
      request.get("/api/user", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res)
        this.loading = false
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },

    save(){
      if (this.form.id) {  // 更新
        request.put("/api/user", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "更新成功"
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
      } else {  // 新增
        request.post("/api/user", this.form).then(res => {
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
      }


    },

    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible=true

    },
    handleSizeChange(pageSize){//改变当前每页的个数触发
      this.pageSize=pageSize
      this.load()

    },
    handleCurrentChange(pageNum){//改变当前页码触发
      this.currentPage=pageNum
      this.load()

    },
    handleDelete(id) {
      console.log(id)
      request.delete("/api/user/" + id).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()  // 删除之后重新加载表格的数据
      })
    },



  },


}
</script>
