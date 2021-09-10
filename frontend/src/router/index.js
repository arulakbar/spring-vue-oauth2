import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
// import Profile from "../components/Profile";
import Login from "../components/Login";
import Callback from "../components/Callback";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/oauth2/redirect",
    component: Callback,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// router.beforeEach((to, from, next) => {
//   let requiresAuth = to.matched.some((value) => value.meta.requiresAuth);
//   let currentUser = localStorage.getItem("currentUser");

//   if ((requiresAuth && !currentUser) || (requiresAuth && currentUser === {})) {
//     next("/login");
//   } else {
//     next();
//   }
// });

export default router;
