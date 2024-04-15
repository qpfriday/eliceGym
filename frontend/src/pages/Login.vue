<script>
import {reactive} from "vue";
import axios from "axios";
import store from "@/scripts/store";
import router from "@/scripts/router";

export default {
  setup() {
    const state = reactive({
      form: {
        loginId: "",
        password: ""
      }
    })
    const login = () => {
      axios.post("/api/account/login", state.form).then((res) => {
        store.commit("setAccount", res.data);
        sessionStorage.setItem("id", res.data)
        router.push({path: "/"})
        window.alert("로그인하였습니다.")
      }).catch(() => {
        window.alert("로그인 정보가 존재하지 않습니다.")
      })
    }
    return {state, login}
  },
}
</script>

<template>
  <div class="container">
    <main class="form-signin">
      <h1 class="h3 mb-3 fw-normal text-center">로그인</h1>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">아이디</label>
        <input type="text" class="form-control" id="floatingInput" placeholder="ID" style="margin-bottom: 20px"
               @keyup.enter="login()"
               v-model="state.form.loginId">
      </div>
      <div class="form-floating">
        <label for="floatingPassword" class="form-label">비밀번호</label>
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password" style="margin-bottom: 20px"
               @keyup.enter="login()"
               v-model="state.form.password">
      </div>
      <button class="w-100 btn btn-lg btn-success" @click="login">로그인</button>
      <a href="join">회원가입 하러가기</a>
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
</style>