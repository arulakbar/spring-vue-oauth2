<template>
  <main class="showcase grid">
    <div class="container">
      <h1>Welcome {{ isAuthenticated }}</h1>
    </div>
  </main>
</template>

<script>
// @ is an alias to /src
import { mapGetters } from "vuex";

export default {
  name: "Home",
  components: {},
  async created() {
    await this.$store.dispatch("fetchUser");
  },
  computed: {
    ...mapGetters(["isAuthenticated"]),
  },
};
</script>

<style scoped>
.showcase {
  background-color: var(--primary-color);
  height: 80vh;
}

.grid {
  display: grid;
  place-content: center;
}

h1 {
  font-size: 3rem;
  color: var(--secondary-color);
}
</style>
