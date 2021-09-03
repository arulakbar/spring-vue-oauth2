<template>
  <div class="showcase grid">
    <div class="container card">
      <!-- <div class="carding"> -->
      <h1 class="title">Login</h1>
      <a @click="login">
        <span>Sign in with github</span>
      </a>
      <!-- </div> -->
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      response: [],
      errors: [],
    };
  },
  methods: {
    login() {
      window.location.href =
        "http://localhost:8080/oauth2/authorization/github";
    },
  },
};
</script>

<style scoped>
.showcase {
  background-color: var(--secondary-color);
  height: 80vh;
}
.grid {
  display: grid;
  place-content: center;
}
.card {
  background: #fff;
  box-shadow: 0 1px 11px rgba(0, 0, 0, 0.27);
  border-radius: 2px;
  width: 500px;
  /* display: inline-block; */
  margin-top: 30px;
  /* vertical-align: middle; */
  /* position: relative; */
  display: flex;
  flex-direction: column;
  padding: 35px;
}
.card .title {
  font-size: 1.5em;
  font-weight: 500;
  margin-top: 0;
  margin-bottom: 30px;
  color: rgba(0, 0, 0, 0.65);
  align-self: center;
}
.card a {
  margin-bottom: 15px;
  font-weight: 400;
  font-size: 16px;
  align-self: center;
}

.card a:hover {
  color: var(--primary-color);
  cursor: pointer;
}
</style>
