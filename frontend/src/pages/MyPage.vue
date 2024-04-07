<script>
import {reactive} from "vue";

export default {
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
          <router-link to="/orders" class="btn btn-outline-dark" style="min-width: 500px; height: 400px"><h1>주문 조회</h1></router-link>
        </div>
        <div class="col-sm-6">
          <router-link to="/info" class="btn btn-outline-dark" style="min-width: 500px; height: 400px"><h1>회원 정보 수정</h1></router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>