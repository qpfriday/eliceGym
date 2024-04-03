<template>
  <header>
    <nav class="py-2 bg-body-tertiary border-bottom">
      <div class="container d-flex flex-wrap justify-content-between align-items-center">
        <ul class="nav d-flex flex-wrap">
          <li>
            <router-link class="nav-link link-body-emphasis px-2 active" to="/"><b>eliceGym</b></router-link>
          </li>
        </ul>
        <ul class="nav d-flex flex-wrap">
          <li v-if="$store.state.account.id">
            <ul class="d-flex flex-wrap">
              <li class="nav-link link-body-emphasis px-2 active">
                {{ $store.state.account.name }} 님
              </li>
              <li>
                <router-link class="nav-link link-body-emphasis px-2 active" to="/my">마이페이지</router-link>
              </li>
              <li>
                <router-link class="nav-link link-body-emphasis px-2 active" to="/cart">장바구니</router-link>
              </li>
              <li><a class="nav-link link-body-emphasis px-2 active" @click="logout()">로그아웃</a></li>
            </ul>
          </li>
          <li v-else>
            <ul class="d-flex flex-wrap">
              <router-link class="nav-link link-body-emphasis px-2 active" to="/join">회원가입</router-link>
              <router-link class="nav-link link-body-emphasis px-2 active" to="/login">로그인</router-link>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
</template>

<script>

import store from "@/scripts/store";
import router from "@/scripts/router";
import axios from "axios";

export default {
  name: 'Header',
  setup() {
    const logout = () => {
      axios.post("/api/account/logout").then(() => {
        store.commit('setAccount', 0);
        router.push({path: "/"})
      });
    }
    return {logout}
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
header nav {
  background-color: antiquewhite;
}

nav ul li .nav-link {
  color: #003b05;
}

header .navbar .cart {
  margin-left: auto;
  color: #fff
}

header ul li a {
  cursor: pointer;
}

ul {
  list-style: none;
}
</style>
