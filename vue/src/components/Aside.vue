<template>
<div>

  <el-row class="tac">
    <el-col :span="12">

      <el-menu
          style="width: 150px;min-height: calc(100vh - 50px)"
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose">
        <el-menu-item index="1">
          <template #title>
            <i class="el-icon-location"></i>
            <span>报名</span>
          </template>


          </el-menu-item>

        <el-menu-item index="2">
          <i class="el-icon-menu"></i>
          <template #title>查询</template>
        </el-menu-item>
        <el-menu-item index="3" disabled>
          <i class="el-icon-document"></i>
          <template #title>导航三</template>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-setting"></i>
          <template #title>导航四</template>
        </el-menu-item>
      </el-menu>
    </el-col>

  </el-row>
</div>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>