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
  <div class="wrap">
    <h2>회원 정보</h2>
    <div class="box">
      <table>
        <tbody>
        <tr>
          <td>이름</td>
          <td>:</td>
          <td>
            <label v-if="!state.editable" for="name">{{state.form.name}}</label>
            <input v-else v-model="state.form.name" id="name" type="text">
          </td>
        </tr>
        <tr>
          <td>아이디</td>
          <td>:</td>
          <td>
            <label v-if="!state.editable" for="id">{{state.form.loginId}}</label>
            <input v-else v-model="state.form.loginId" id="id" type="text" disabled>
          </td>
        </tr>
        <tr>
          <td>이메일</td>
          <td>:</td>
          <td>
            <label v-if="!state.editable" for="email">{{state.form.email}}</label>
            <input v-else v-model="state.form.email" id="email" type="email" disabled>
          </td>
        </tr>
        <tr>
          <td>주소</td>
          <td>:</td>
          <td>
            <label v-if="!state.editable" for="deliveryAddress">{{state.form.deliveryAddress}}</label>
            <input v-else v-model="state.form.deliveryAddress" id="deliveryAddress" type="text">
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <button @click="edit" v-if="!state.editable">수정</button>
    <button @click="save" v-if="state.editable">저장</button>
  </div>
</template>

<style scoped>
  .wrap {
    margin: 50px auto;
    text-align:center;
    font-size: 16px;
    padding: 0 10px;
    width: 80%;
  }

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
</style>