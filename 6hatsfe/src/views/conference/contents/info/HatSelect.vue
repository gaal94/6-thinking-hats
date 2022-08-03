<template>
  <div class="hat-select-box">
    <img src="" alt="" class="hat-img">
    <div class="user-list">
      <user-list-item></user-list-item>
    </div>
  </div>
</template>

<script>
import UserListItem from '@/views/conference/contents/info/UserListItem.vue'

export default {
  name: 'HatSelect',
  components: {
    UserListItem,
  }
}
</script>

<style>

</style>