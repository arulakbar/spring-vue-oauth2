<template>
  <h1>Redirecting...</h1>
</template>

<script>
export default {
  async created() {
    await this.$store.dispatch("setToken", {
      code: this.$route.query.code,
      state: this.$route.query.state,
    });
    this.$router.replace("/");
  },
};
</script>
