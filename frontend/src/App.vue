<template>
  <Header />
  <RouterView />
  <Footer />
</template>

<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
import store from "@/scripts/store";
import axios from "axios";
import { useRoute } from "vue-router";
import { watch } from "vue";

export default {
  name: "App",
  components: {
    Footer,
    Header,
  },
  setup() {
    const check = async () => {
      try {
        const { data } = await axios.get("/api/account/check");
        console.log(data);
        store.commit("setAccount", data || 0);
      } catch (error) {
        console.error("에러 발생:", error);
      }
    };

    const route = useRoute();
    watch(route, async () => {
      await check();
    });
  },
};
</script>

<style></style>
