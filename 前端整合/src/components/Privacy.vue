<template>
  <div class="privacy">
    <div class="like">
      <p>
        公开我的收藏夹
      </p>
      <p>
        <el-switch
          v-model="value2"
          active-text="开"
          inactive-text="关">
        </el-switch>
      </p>
    </div>

  </div>
</template>
<script>
  export default {
    data() {
      return {
        value1: true,
        value2: true
      }
    },
    methods:{

    }
  };
</script>
