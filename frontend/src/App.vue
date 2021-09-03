<template>
  <div>
    <Navbar />
    <router-view></router-view>
  </div>
</template>

<script>
import Navbar from "./components/Navbar.vue";

export default {
  components: { Navbar },
};
</script>
<style>
:root {
  --primary-color: #047aed;
  --secondary-color: #ecf0f1;
}

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

ul {
  list-style-type: none;
}

a {
  text-decoration: none;
}

.container {
  max-width: 1400px;
  margin: 0 auto;
  overflow: hidden;
  padding: 0 40px;
}
</style>
