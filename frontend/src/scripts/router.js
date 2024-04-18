//
// import Home from "@/pages/Home.vue";
// import Login from "@/pages/Login.vue";
// import Join from "@/pages/Join.vue";
// import MyPage from "@/pages/MyPage.vue";
// import MyInfo from "@/pages/MyInfo.vue";
// import Cart from "@/pages/Cart.vue";
// import Order from "@/pages/Order.vue";
// import Orders from "@/pages/Orders.vue";
// import ProductDetail from "@/pages/ProductDetail.vue";
// import AddItem from "@/pages/AddItem.vue";
// import CategoryManagement from "@/pages/CategoryManagement.vue";
// import AddCategory from "@/pages/AddCategory.vue";
// import ModifyCategory from "@/pages/ModifyCategory.vue";
// import ModifyItem from "@/pages/ModifyItem.vue";
// import { createRouter, createWebHistory } from "vue-router";
//
//
// // function checkIfUserIsLoggedIn() {
// //   const store = useStore();
// //   // Vuex 스토어에서 로그인 상태를 가져온다
// //   return store.getters.isLoggedIn;
// // }
// // // 미들웨어 함수 정의
// // const requireAuth = (to, from, next) => {
// //   const isLoggedIn = checkIfUserIsLoggedIn();
// //
// //   // 로그인하지 않은 경우 로그인 페이지로 리다이렉션
// //   if (!isLoggedIn) {
// //     next("/login");
// //   } else {
// //     next();
// //   }
// // };
//
// const routes = [
//   { path: "/", component: Home },
//   { path: "/login", component: Login },
//   { path: "/join", component: Join },
//   { path: "/my", component: MyPage},
//   { path: "/info", component: MyInfo},
//   { path: "/cart", component: Cart},
//   { path: "/order", component: Order},
//   { path: "/orders", component: Orders},
//   { path: "/item/:itemId", component: ProductDetail, name: "productDetail" },
//   { path: "/addItem", component: AddItem},
//   {
//     path: "/categoryManagement",
//     component: CategoryManagement,
//   },
//   { path: "/add-category", component: AddCategory},
//   {
//     path: "/modify-category",
//     component: ModifyCategory,
//   },
//   {
//     path: "/item/:itemId/modify-item",
//     component: ModifyItem,
//     name: "modifyItem",
//   },
//   { path: "/:pathMatch(.*)", redirect: "/" }, // 루트 경로로 리다이렉션
// ];
//
// const router = createRouter({
//   history: createWebHistory(),
//   routes: routes,
// });
//
// export default router;

import Home from "@/pages/Home.vue";
import Login from "@/pages/Login.vue";
import Join from "@/pages/Join.vue";
import MyPage from "@/pages/MyPage.vue";
import MyInfo from "@/pages/MyInfo.vue";
import Cart from "@/pages/Cart.vue";
import Order from "@/pages/Order.vue";
import Orders from "@/pages/Orders.vue";
import ProductDetail from "@/pages/ProductDetail.vue";
import AddItem from "@/pages/AddItem.vue";
import CategoryManagement from "@/pages/CategoryManagement.vue";
import AddCategory from "@/pages/AddCategory.vue";
import ModifyCategory from "@/pages/ModifyCategory.vue";
import ModifyItem from "@/pages/ModifyItem.vue";
import { createRouter, createWebHistory } from "vue-router";

// 라우터 링크 상수 정의
const ROUTER_LINKS = {
  HOME: { path: '/'},
  LOGIN: { path: '/login'},
  JOIN: { path: '/join'},
  MY_PAGE: { path: '/my'},
  MY_INFO: { path: '/info'},
  CART: { path: '/cart'},
  ORDER: { path: '/order'},
  ORDERS: { path: '/orders'},
  PRODUCT_DETAIL: { path: '/item/:itemId'},
  ADD_ITEM: { path: "/addItem"},
  CATEGORY_MANAGEMENT: { path: "/categoryManagement"},
  ADD_CATEGORY: { path: "/add-category"},
  MODIFY_CATEGORY: { path: "/modify-category/:id"},
  MODIFY_ITEM: { path: "/item/:itemId/modify-item"},
};

// 라우터 경로 설정
const routes = [
  { path: ROUTER_LINKS.HOME.path, component: Home },
  { path: ROUTER_LINKS.LOGIN.path, component: Login },
  { path: ROUTER_LINKS.JOIN.path, component: Join },
  { path: ROUTER_LINKS.MY_PAGE.path, component: MyPage },
  { path:ROUTER_LINKS.MY_INFO.path, component: MyInfo },
  { path: ROUTER_LINKS.CART.path, component: Cart },
  { path: ROUTER_LINKS.ORDER.path, component: Order },
  { path: ROUTER_LINKS.ORDERS.path, component: Orders },
  { path: ROUTER_LINKS.PRODUCT_DETAIL.path, component: ProductDetail, name: "productDetail" },
  { path: ROUTER_LINKS.ADD_ITEM.path, component: AddItem },
  { path: ROUTER_LINKS.CATEGORY_MANAGEMENT.path, component: CategoryManagement, },
  { path: ROUTER_LINKS.ADD_CATEGORY.path, component: AddCategory },
  { path: ROUTER_LINKS.MODIFY_CATEGORY.path, component: ModifyCategory },
  { path: ROUTER_LINKS.MODIFY_ITEM.path, component: ModifyItem, name: "modifyItem" }
];

console.log("routes", routes)
console.log("ROUTER_LINKS", ROUTER_LINKS)
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

export { router, ROUTER_LINKS };
