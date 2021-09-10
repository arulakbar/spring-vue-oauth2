<template>
  <h1>Loading...</h1>
</template>

<script>
export default {
  async created() {
    // console.log(this.$route.query);
    await this.$store.dispatch("setToken", {
      token: this.$route.query.token,
      // state: this.$route.query.state,
    });
    await this.$store.dispatch("fetchUser");
    this.$router.replace("/");
  },
};
</script>
