<script>
import {computed, reactive} from "vue";
import axios from "axios";
import router from "@/scripts/router";

export default {
  setup() {
    const state = reactive({
      form: {
        name: "",
        loginId: "",
        password: "",
        confirmPassword: "", // New field for confirmation password
        email: "",
        phoneNumber: "",
        role: "",
      }
    });

    const validateForm = () => {
      return (
          state.form.name &&
          state.form.loginId &&
          state.form.password &&
          state.form.confirmPassword && // Check confirmation password
          state.form.password === state.form.confirmPassword && // Check if password matches confirmation password
          state.form.email &&
          state.form.phoneNumber &&
          state.form.role
      );
    };


    const join = () => {
      if (validateForm()) {
        axios
            .post("/api/account/join", state.form)
            .then((response) => {
              if (response.status === 200) {
                router.push({ path: "/login" });
                window.alert("회원가입이 완료되었습니다. 로그인 해주세요.");
              }
            })
            .catch((error) => {
              if (error.response && error.response.status === 400) {
                window.alert("이미 존재하는 아이디입니다.");
              } else {
                window.alert("회원가입에 실패하였습니다. 다시 시도해주세요.");
              }
            });
      } else {
        window.alert("모든 필드를 입력해 주세요.");
      }
    };
    const setRole = (selectedRole) => {
      state.form.role = selectedRole;
    };

    const isFormValid = computed(() => validateForm());

    return { state, join, isFormValid, setRole };
  }
}
</script>

<template>
  <div class="container">
    <main class="form-signin">
      <form class="needs-validation" novalidate>
        <h1 class="h3 mb-3 fw-normal text-center">회원가입</h1>
        <div class="form-floating">
          <label for="validationName" class="form-label" style="margin-top: 20px">이름</label>
          <input type="text" class="form-control" id="validationName" placeholder="Name"  required
                 @keyup.enter="join"
                 v-model="state.form.name">
          <div class="invalid-feedback">이름을 입력해 주세요</div>
        </div>
        <div class="form-floating">
          <label for="validationId" class="form-label" style="margin-top: 20px">아이디</label>
          <input type="text" class="form-control" id="validationId" placeholder="ID" required
                 @keyup.enter="join"
                 v-model="state.form.loginId">
          <div class="invalid-feedback">아이디를 입력해 주세요</div>
        </div>
        <div class="form-floating">
          <label for="validationPW" class="form-label" style="margin-top: 20px">비밀번호</label>
          <input type="password" class="form-control" id="validationPW" placeholder="Password" required
                 @keyup.enter="join"
                 v-model="state.form.password">
          <div class="invalid-feedback">비밀번호를 입력해 주세요</div>
        </div>
        <div class="form-floating">
          <label for="validationConfirmPW" class="form-label" style="margin-top: 20px">비밀번호 확인</label>
          <input type="password" class="form-control" id="validationConfirmPW" placeholder="Confirm Password" required
                 @keyup.enter="join"
                 v-model="state.form.confirmPassword">
          <div class="invalid-feedback">비밀번호를 다시 입력해 주세요</div>
        </div>
        <div class="form-floating">
          <label for="validationPN" class="form-label" style="margin-top: 20px">전화번호</label>
          <input type="text" class="form-control" id="validationPN" placeholder="XXX-XXXX-XXXX" required
                 @keyup.enter="join"
                 v-model="state.form.phoneNumber">
          <div class="invalid-feedback">전화번호를 입력해 주세요</div>
        </div>
        <div class="form-floating">
          <label for="validationEmail" class="form-label" style="margin-top: 20px">이메일</label>
          <input type="text" class="form-control" id="validationEmail" placeholder="Email" required
                 @keyup.enter="join"
                 v-model="state.form.email">
          <div class="invalid-feedback">이메일을 입력해 주세요</div>
        </div>
        <div class="form-floating">
          <label for="validationEmail" class="form-label" style="margin-top: 20px">가입 목적</label>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" @change="setRole('ROLE_USER')" checked>
            <label class="form-check-label" for="exampleRadios1">
              구매자
            </label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" @change="setRole('ROLE_SELLER')">
            <label class="form-check-label" for="exampleRadios2">
              판매자
            </label>
          </div>
        </div>
        <button class="w-100 btn btn-lg btn-success" type="submit" style="margin-top: 20px" @click.prevent="join">회원가입</button>
        <a href="login">로그인 하러가기</a>
      </form>
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