<script>
import {reactive} from "vue";
import {ROUTER_LINKS} from "@/scripts/router";

export default {
  computed: {
    ROUTER_LINKS() {
      return ROUTER_LINKS
    }
  },
  setup() {
    const state = reactive({
    })

    return {state}
  },
}
</script>

<template>
  <div class="my">
    <div class="container" style="margin: 100px auto 100px auto">
      <div class="row text-center">
        <div class="col-sm-6">
          <router-link :to="ROUTER_LINKS.ORDERS.path" class="btn btn-outline-success order-button" style="min-width: 500px; height: 400px">
            <h1>주문 조회</h1>
            <img src="@/assets/order.svg" class="order-image">
          </router-link>
        </div>
        <div class="col-sm-6">
          <router-link :to="ROUTER_LINKS.MY_INFO.path" class="btn btn-outline-success pencil-button" style="min-width: 500px; height: 400px">
            <h1>회원 정보 수정</h1>
            <img src="@/assets/pencil.svg" class="pencil-image">
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.order-image,
.pencil-image {
  margin-top: 30px;
  width: 250px;
  height: 250px;
  opacity: 1; /* Initially visible */
  transition: opacity; /* Add transition for smooth effect */
  transition-duration: 2s;
  filter: invert(51%) sepia(76%) saturate(514%) hue-rotate(81deg) brightness(88%) contrast(81%);
}

.order-button:hover .order-image,
.pencil-button:hover .pencil-image {
  filter: invert(100%) sepia(46%) saturate(467%) hue-rotate(87deg) brightness(108%) contrast(98%);
}
</style>