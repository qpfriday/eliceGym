import router from "@/scripts/router";

export const addCommas = (n) => {
  return n.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
};

export const moveToCart = () => {
  router.push({ path: "/cart" });
};
export const moveToLogin = () => {
  router.push({ path: "/login" });
};
