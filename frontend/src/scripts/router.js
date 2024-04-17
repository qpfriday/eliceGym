
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


// function checkIfUserIsLoggedIn() {
//   const store = useStore();
//   // Vuex 스토어에서 로그인 상태를 가져온다
//   return store.getters.isLoggedIn;
// }
// // 미들웨어 함수 정의
// const requireAuth = (to, from, next) => {
//   const isLoggedIn = checkIfUserIsLoggedIn();
//
//   // 로그인하지 않은 경우 로그인 페이지로 리다이렉션
//   if (!isLoggedIn) {
//     next("/login");
//   } else {
//     next();
//   }
// };

const routes = [
  { path: "/", component: Home },
  { path: "/login", component: Login },
  { path: "/join", component: Join },
  { path: "/my", component: MyPage},
  { path: "/info", component: MyInfo},
  { path: "/cart", component: Cart},
  { path: "/order", component: Order},
  { path: "/orders", component: Orders},
  { path: "/item/:itemId", component: ProductDetail, name: "productDetail" },
  { path: "/addItem", component: AddItem},
  {
    path: "/categoryManagement",
    component: CategoryManagement,
  },
  { path: "/add-category", component: AddCategory},
  {
    path: "/modify-category",
    component: ModifyCategory,
  },
  {
    path: "/item/:itemId/modify-item",
    component: ModifyItem,
    name: "modifyItem",
  },
  { path: "/:pathMatch(.*)", redirect: "/" }, // 루트 경로로 리다이렉션
];

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

export default router;

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
// // 라우터 링크 상수 정의
// const ROUTER_LINKS = {
//   HOME: { path: '/', component: Home },
//   LOGIN: { path: '/login', component: Login },
//   JOIN: { path: '/join', component: Join },
//   MY_PAGE: { path: '/my', component: MyPage },
//   MY_INFO: { path: '/info', component: MyInfo },
//   CART: { path: '/cart', component: Cart },
//   ORDER: { path: '/order', component: Order },
//   ORDERS: { path: '/orders', component: Orders },
//   PRODUCT_DETAIL: { path: '/item/:itemId', component: ProductDetail, name: 'productDetail' },
//   ADD_ITEM: { path: "/addItem", component: AddItem },
//   CATEGORY_MANAGEMENT: { path: "/categoryManagement", component: CategoryManagement },
//   ADD_CATEGORY: { path: "/add-category", component: AddCategory },
//   MODIFY_CATEGORY: { path: "/modify-category", component: ModifyCategory },
//   MODIFY_ITEM: { path: "/item/:itemId/modify-item", component: ModifyItem , name: "modifyItem"},
// };
//
// // 라우터 경로 설정
// const routes = Object.values(ROUTER_LINKS).map(link => ({
//   path: link.path,
//   component: link.component,
//   name: link.name
// }));
//
// const router = createRouter({
//   history: createWebHistory(),
//   routes: routes
// });
//
// export { router, ROUTER_LINKS };
