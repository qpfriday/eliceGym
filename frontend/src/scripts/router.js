import Home from "@/pages/Home.vue";
import Login from "@/pages/Login.vue";
import Join from "@/pages/Join.vue";
import MyPage from "@/pages/MyPage.vue";
import Cart from "@/pages/Cart.vue";
import Order from "@/pages/Order.vue";
import Orders from "@/pages/Orders.vue";
import ProductDetail from "@/pages/ProductDetail.vue";
import { createRouter, createWebHistory } from "vue-router";

const routes = [
  { path: "/", component: Home },
  { path: "/login", component: Login },
  { path: "/join", component: Join },
  { path: "/my", component: MyPage },
  { path: "/cart", component: Cart },
  { path: "/order", component: Order },
  { path: "/orders", component: Orders },
  { path: "/:itemId", component: ProductDetail, name: "productDetail" },
];
const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

export default router;
