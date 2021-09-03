<template>
  <div id="nav">
    <Header />
   <div style="display: flex">
     <Aside />
     <router-view style="flex: 1px"/>



   </div>
  </div>

</template>

<style>

</style>

<script>


import Header from "@/components/Header";
import Aside from "@/components/Aside";

export default {
  name: 'Layout',
  components: {
    Aside,
    Header

  }
}
</script>
