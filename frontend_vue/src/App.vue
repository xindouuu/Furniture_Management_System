<template>
  <div>
    <Header/>
    <div style="display:flex">
      <Aside/>
      <!-- Content area, this section is derived
      from the HomeView.vue component (via the routing mechanism) -->
        <router-view style="flex: 1"/>
    </div>
  </div>
</template>

<style>
</style>

<script>
  import Header from "@/components/Header";//@=src
  import Aside from "@/components/Aside";
  export default {
    name: "Layout",
    components: {
      Header,
      Aside
    }
  }
</script>