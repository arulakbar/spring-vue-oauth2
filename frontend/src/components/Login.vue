<template>
  <div class="showcase grid">
    <div class="container card">
      <h1 class="title">Sign In to App</h1>

      <button class="button" @click="login">
        <img class="button-img" src="github-logo.png" alt="" />
        <span class="button-text"> Sign in with Github</span>
      </button>
      <button class="button">
        <img class="button-img" src="google-logo.png" alt="" />
        <span class="button-text"> Sign in with Google</span>
      </button>
      <div class="or-separator">
        <div class="or-text">OR</div>
      </div>
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
  margin-top: 30px;
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

.button {
  display: inline-flex;
  height: 45px;
  padding: 0;
  background: none;
  border: solid 1px rgb(226, 224, 224);
  outline: none;
  border-radius: 2px;
  overflow: hidden;
  font-size: 16px;
  margin-bottom: 20px;
}

.button:hover {
  color: var(--secondary-color);
  background: var(--primary-color);
  cursor: pointer;
}

.button-text,
.button-img {
  display: inline-flex;
  align-items: center;
  padding: 0 40px;
}

.button-img {
  height: 70%;
  align-self: center;
}
.or-separator {
  border-bottom: 1px solid #eee;
  padding: 10px 0;
  position: relative;
  display: block;
  margin-top: 10px;
  margin-bottom: 30px;
  font-size: 1em;
}

.or-text {
  position: absolute;
  left: 46%;
  top: 0;
  background: #fff;
  padding: 10px;
  color: rgba(0, 0, 0, 0.45);
}
</style>
