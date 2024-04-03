<script>
import {reactive} from "vue";
import axios from "axios";
import store from "@/scripts/store";
import router from "@/scripts/router";

export default {
  setup() {
    const state = reactive({
      form: {
        name: "",
        loginId: "",
        password: "",
        email: "",
      }
    })
    const join = () => {
      axios.post("/api/account/join", state.form).then(() => {
        router.push({path: "/login"})
        window.alert("회원가입이 완료되었습니다. 로그인 해주세요.")
      }).catch(() => {
        window.alert("회원가입에 실패하였습니다. 다시 시도해주세요.")
      })
    }
    return {state, join}
  },
}
</script>

<template>
  <div class="container">
    <main class="form-signin">
      <h1 class="h3 mb-3 fw-normal text-center">회원가입</h1>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingInput" placeholder="Name"
               @keyup.enter="join()"
               v-model="state.form.name">
        <label for="floatingInput">Name</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingInput" placeholder="ID"
               @keyup.enter="join()"
               v-model="state.form.loginId">
        <label for="floatingInput">ID</label>
      </div>
      <div class="form-floating">
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password"
               @keyup.enter="join()"
               v-model="state.form.password">
        <label for="floatingPassword">Password</label>
      </div>
      <div class="form-floating">
        <input type="email" class="form-control" id="floatingInput" placeholder="Email"
               @keyup.enter="join()"
               v-model="state.form.email">
        <label for="floatingInput">Email</label>
      </div>
      <button class="w-100 btn btn-lg btn-success" @click="join">회원가입</button>
      <a href="login">로그인 하러가기</a>
    </main>
  </div>
</template>

<style scoped>
.container {
  margin-top: 100px;
  width: 500px;
  padding: 100px 50px 100px 50px;
  border: solid lightgray 0.5px;
  border-radius: 10px
}
div .form-control {
  margin: 15px auto 15px auto;
}
</style>