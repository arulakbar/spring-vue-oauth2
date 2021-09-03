<template>
  <div class="navbar">
    <div class="container flex">
      <h1 class="logo">The <span>Logo</span></h1>
      <nav>
        <ul>
          <li><router-link to="/">Home</router-link></li>
          <li>
            <router-link to="/login" v-if="!isAuthenticated">Login</router-link>
            <router-link to="#" v-else @click="handleLogout"
              >Logout</router-link
            >
          </li>
          <li><router-link to="#">About us</router-link></li>
        </ul>
      </nav>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["isAuthenticated"]),
  },
  methods: {
    handleLogout() {
      this.$store.dispatch("logout");
      this.$router.replace("/");
    },
  },
};
</script>

<style scoped>
.navbar {
  background: var(--secondary-color);
  color: var(--primary-color);
  height: 70px;
}

h1 span {
  color: black;
}

.flex {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
}

.navbar ul {
  display: flex;
}

ul a {
  color: var(--primary-color);
  padding: 10px;
  margin: 0 5px;
}

ul a:hover {
  background-color: var(--primary-color);
  color: var(--secondary-color);
}
</style>
