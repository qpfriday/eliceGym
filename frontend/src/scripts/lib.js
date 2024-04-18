import {router, ROUTER_LINKS} from "@/scripts/router";

export const addCommas = (n) => {
  return n.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
};

export const moveToCart = () => {
  router.push(ROUTER_LINKS.CART.path);
};
export const moveToLogin = () => {
  router.push(ROUTER_LINKS.LOGIN.path);
};

export const formatDate = (date) => {
  return new Date(date).toLocaleDateString("ko-KR", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
  });
};

export const formatTime = (date) => {
  return new Date(date).toLocaleTimeString("ko-KR", {
    hour: "2-digit",
    minute: "2-digit",
  });
};
export const openPostCode = (state) => {
  new window.daum.Postcode({
    oncomplete: function (data) {
      var roadAddr = data.roadAddress;

      document.getElementById("postcode").value = data.zonecode;
      document.getElementById("roadAddress").value = roadAddr;
      document.getElementById("jibunAddress").value = data.jibunAddress;

      state.form.address1 = roadAddr;
      state.form.postCode = data.zonecode;
    },
    theme: {},
  }).open({
    left: -1300,
    top: 200,
  });
};
