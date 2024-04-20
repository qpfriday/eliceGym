<script>
import { reactive } from "vue";
//import axios from "axios";
import { openPostCode } from "@/scripts/lib";
import { router, ROUTER_LINKS } from "@/scripts/router";
import store from "@/scripts/store";
import baseURL from "@/scripts/baseURL";

export default {
  methods: { openPostCode },
  setup() {
    const state = reactive({
      form: {
        name: "",
        loginId: "",
        email: "",
        deliveryAddress: "",
        phoneNumber: "",
        postCode: "",
        address1: "",
        address2: "",
      },
      editable: false,
    });

    const load = () => {
      baseURL.get("/api/account/info").then(({ data }) => {
        console.log(data);
        state.form = data;
      });
    };

    const addrStatus = (boo) => {
      document.getElementById("postcode").disabled = boo;
      document.getElementById("roadAddress").disabled = boo;
      document.getElementById("detailAddress").disabled = boo;
      document.getElementById("jibunAddress").disabled = boo;
      document.getElementById("addressBtn").hidden = boo;
    };

    const edit = () => {
      state.editable = true;
      addrStatus(false);
    };

    const save = async () => {
      console.log(state.form);
      const res = await baseURL.put("/api/account/update", state.form);
      console.log(res);
      alert("수정이 완료되었습니다");
      state.editable = false;
      addrStatus(true);
    };

    const deleteUser = async () => {
      if (confirm("정말로 계정을 삭제하시겠습니까?")) {
        const res = await baseURL.delete("/api/account/delete");
        console.log(res);
        alert("계정이 삭제되었습니다");
        // 계정 삭제 후, 로그인 페이지로 이동하거나 다른 작업 수행
      }
      baseURL.post("/api/account/logout").then(() => {
        store.commit("resetAccount");
        router.push(ROUTER_LINKS.HOME.path);
      });
    };

    load();

    const clickAddress = () => {
      openPostCode(state);
    };

    return { state, edit, save, deleteUser, clickAddress };
  },
};
</script>

<template>
  <div class="container">
    <main class="form-signin">
      <h1 class="h3 mb-3 fw-normal text-center">회원 정보</h1>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">이름</label>
        <input
          type="text"
          class="form-control"
          id="floatingInput"
          placeholder="Name"
          aria-label="Disabled input example"
          disabled
          style="margin-bottom: 20px"
          v-if="!state.editable"
          v-model="state.form.name"
        />
        <input
          type="text"
          class="form-control"
          id="floatingInput"
          placeholder="Name"
          required
          style="margin-bottom: 20px"
          v-else
          v-model="state.form.name"
        />
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">아이디</label>
        <input
          type="text"
          class="form-control"
          id="floatingInput"
          placeholder="Disabled"
          aria-label="Disabled input example"
          disabled
          style="margin-bottom: 20px"
          v-if="!state.editable"
          v-model="state.form.loginId"
        />
        <input
          type="text"
          class="form-control"
          id="floatingInput"
          placeholder="ID"
          required
          style="margin-bottom: 20px"
          v-else
          v-model="state.form.loginId"
        />
      </div>
      <div class="form-floating">
        <label for="floatingPassword" class="form-label">비밀번호</label>
        <input
          type="password"
          class="form-control"
          id="floatingPassword"
          placeholder="Password"
          aria-label="Disabled input example"
          disabled
          style="margin-bottom: 20px"
          v-if="!state.editable"
          v-model="state.form.password"
        />
        <input
          type="password"
          class="form-control"
          id="floatingPassword"
          placeholder="Password"
          required
          style="margin-bottom: 20px"
          v-else
          v-model="state.form.password"
        />
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">전화번호</label>
        <input
          type="text"
          class="form-control"
          id="floatingInput"
          placeholder="XXX-XXXX-XXXX"
          aria-label="Disabled input example"
          disabled
          style="margin-bottom: 20px"
          v-if="!state.editable"
          v-model="state.form.phoneNumber"
        />
        <input
          type="text"
          class="form-control"
          id="floatingInput"
          placeholder="XXX-XXXX-XXXX"
          required
          style="margin-bottom: 20px"
          v-else
          v-model="state.form.phoneNumber"
        />
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">이메일</label>
        <input
          type="text"
          class="form-control"
          id="floatingInput"
          placeholder="Email"
          aria-label="Disabled input example"
          disabled
          style="margin-bottom: 20px"
          v-if="!state.editable"
          v-model="state.form.email"
        />
        <input
          type="email"
          class="form-control"
          id="floatingInput"
          placeholder="Email"
          required
          style="margin-bottom: 20px"
          v-else
          v-model="state.form.email"
        />
      </div>
      <div class="col-12 address-fields">
        <label for="address" class="form-label">주소</label>
        <input
          disabled
          type="text"
          id="postcode"
          placeholder="우편번호"
          class="form-control"
          v-model="state.form.postCode"
        />
        <button
          id="addressBtn"
          hidden
          class="btn btn-success"
          style="margin: 8px 0 8px 0"
          type="button"
          @click="clickAddress"
        >
          우편번호 찾기
        </button>
        <input
          disabled
          type="text"
          class="form-control"
          id="roadAddress"
          placeholder="도로명주소"
          v-model="state.form.address1"
        />
        <input
          disabled
          type="text"
          id="jibunAddress"
          placeholder="지번주소"
          class="form-control"
        />
        <span id="guide" style="color: #999; display: none"></span>
        <input
          disabled
          type="text"
          class="form-control"
          id="detailAddress"
          placeholder="상세주소"
          v-model="state.form.address2"
        />
      </div>
      <button
        class="w-100 btn btn-lg btn-success"
        @click="edit"
        v-if="!state.editable"
      >
        수정
      </button>
      <button
        class="w-100 btn btn-lg btn-success"
        @click="save"
        v-if="state.editable"
      >
        저장
      </button>
      <div class="d-flex justify-content-center align-items-center my-3">
        <button class="btn btn-outline-danger" @click="deleteUser">
          회원 탈퇴
        </button>
      </div>
    </main>
  </div>
</template>

<style scoped>
.container {
  margin: 50px auto 100px auto;
  width: 700px;
  padding: 100px 50px 100px 50px;
  border: solid lightgray 0.5px;
  border-radius: 10px;
}
.address-fields {
  display: flex;
  gap: 5px;
  flex-wrap: wrap;
  padding: 0;
}
.address-fields label {
  width: 100%;
  display: block;
}
#postcode,
#roadAddress,
#jibunAddress,
#detailAddress {
  width: 48%;
  padding: 10px 10px;
  margin: 8px 0;
  box-sizing: border-box;
}
</style>
