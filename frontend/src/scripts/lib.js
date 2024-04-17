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
