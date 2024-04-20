<script>
import { reactive } from "vue";

export default {
  name: "ResultModal",
  props: {
    moveFunction: {
      type: Function,
      required: true,
    },
    closeFunction: {
      type: Function,
      required: true,
    },
    title: {
      type: String,
      required: true,
    },
    content: {
      type: String,
      required: true,
    },
    btn1: {
      type: String,
      required: true,
    },
    btn2: {
      type: String,
      required: true,
    },
  },
  setup(props) {
    console.log(props.title);
    const state = reactive({});

    return { state };
  },
};
</script>

<template>
  <div class="modal" tabindex="-1">
    <div class="backdrop"></div>

    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">{{ title }}</h5>
          <button
            type="button"
            class="close"
            data-dismiss="modal"
            aria-label="Close"
            @click="closeFunction"
          >
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <p>{{ content }}</p>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-dismiss="modal"
            @click="closeFunction"
          >
            {{ btn1 }}
          </button>
          <button @click="moveFunction" type="button" class="btn btn-success">
            {{ btn2 }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.modal {
  display: block;
  position: fixed;
  z-index: 1050;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
  outline: 0;
}
.backdrop {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(2px);
}
</style>
