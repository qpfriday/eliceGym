<script>
import {reactive} from "vue";
import axios from "axios";

export default {
  setup() {
    const state = reactive({
      form: {
        name: "",
        loginId: "",
        email: "",
        deliveryAddress: ""
      },
      editable: false
    })

    const load = () => {
      axios.get("/api/account/info").then(({data}) => {
        console.log(data)
        state.form = data
      })
    }

    const edit = () => {
      state.editable = true
    }

    const save = () => {
      console.log(state.form)
      axios.put("/api/account/update", state.form)
      state.editable = false
    }

    load()

    return { state, edit, save }
  }
}
</script>

<template>
  <div class="container">
    <main class="form-signin">
      <h1 class="h3 mb-3 fw-normal text-center">회원 정보</h1>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">이름</label>
        <input type="text" class="form-control" id="floatingInput" placeholder="Name" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.name">
        <input type="text" class="form-control" id="floatingInput" placeholder="Name"  required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.name">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">아이디</label>
        <input type="text" class="form-control" id="floatingInput" placeholder="Disabled" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.loginId">
        <input type="text" class="form-control" id="floatingInput" placeholder="ID" required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.loginId">
      </div>
      <div class="form-floating">
        <label for="floatingPassword" class="form-label">비밀번호</label>
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.password">
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password" required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.password">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">전화번호</label>
        <input type="text" class="form-control" id="floatingInput" placeholder="XXX-XXXX-XXXX" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.phoneNumber">
        <input type="text" class="form-control" id="floatingInput" placeholder="XXX-XXXX-XXXX" required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.phoneNumber">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">이메일</label>
        <input type="text" class="form-control" id="floatingInput" placeholder="Email" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.email">
        <input type="email" class="form-control" id="floatingInput" placeholder="Email" required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.email">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">배송지</label>
        <textarea class="form-control" id="floatingInput" placeholder="Address" rows="3" disabled
               style="margin-bottom: 50px"
               v-if="!state.editable"
               v-model="state.form.deliveryAddress"></textarea>
        <textarea class="form-control" id="floatingInput" placeholder="Address" rows="3" required
               style="margin-bottom: 50px"
               v-else
               v-model="state.form.deliveryAddress"></textarea>
      </div>
      <button class="w-100 btn btn-lg btn-success" @click="edit" v-if="!state.editable">수정</button>
      <button class="w-100 btn btn-lg btn-success" @click="save" v-if="state.editable">저장</button>
    </main>
  </div>
</template>

<style scoped>

  table {
    border-collapse: collapse;
    width: 500px;
    margin: 50px auto;
    box-shadow: 4px 4px 10px 0 rgba(0, 0, 0, 0.1);
    background-color: white;
  }
  th, td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
  }
  .container {
    margin: 50px auto 100px auto;
    width: 700px;
    padding: 100px 50px 100px 50px;
    border: solid lightgray 0.5px;
    border-radius: 10px
  }
</style>