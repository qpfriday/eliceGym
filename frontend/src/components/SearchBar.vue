<script>
export default {
  name: "SearchBar",
  data() {
    return {
      searchText: ''
    };
  },
  methods: {
    emitSearch() {
      this.$emit('search', this.searchText);
    }
  }
}
</script>

<template>
  <div class="container" style="padding: 0 0 0 15px">
    <div class="justify-content-end">
        <div class="input-group">
          <input type="text" class="form-control" placeholder="Search..." @keyup.enter="emitSearch" v-model="searchText">
          <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button" @click="emitSearch">검색</button>
          </div>
        </div>
    </div>
  </div>
</template>

<style scoped>
</style>