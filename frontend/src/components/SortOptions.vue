<script>
export default {
  name: "SortOptions",
  props: {
    options: Array,
  },
  mounted() {
    console.log('Received options:', this.options);
  },
  methods: {
    selectOption(value) {
      this.$emit('option-selected', value);
    }
  }
}
</script>

<template>
  <div class="container col-9">
  <div class="row justify-content-end sort-container">
      <div class="dropdown my-2">
        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
          정렬 기준
        </button>
        <ul class="dropdown-menu">
          <li v-for="option in options" :key="option.value">
            <a class="dropdown-item" @click="selectOption(option.value)">{{ option.text }}</a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<style scoped>
.dropdown-menu {
  cursor: pointer;
}
</style>