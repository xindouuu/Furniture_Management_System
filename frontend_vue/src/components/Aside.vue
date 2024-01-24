<template>
  <div>
    <el-menu
        style="width: 200px"
        default-active="2"
        class="el-menu-vertical-demo"
    >
      <el-sub-menu index="1-4">
        <template #title>Option</template>
        <el-menu-item index="1-4-1">Option-1</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="2">
        <i class="el-icon-menu"></i>
        <template #title>Navi-1</template>
      </el-menu-item>
      <el-menu-item index="3" disabled>
        <i class="el-icon-document"></i>
        <template #title>Navi-2</template>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-setting"></i>
        <template #title>Navi-3</template>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>