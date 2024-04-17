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

export const formatDate = (date) => {
  return new Date(date).toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit'
  })
}

export const formatTime = (date) => {
  return new Date(date).toLocaleTimeString('ko-KR', {
    hour: '2-digit',
    minute: '2-digit'
  })
}