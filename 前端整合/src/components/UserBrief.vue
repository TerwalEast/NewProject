<template>
    <div id="userBrief">
        <el-avatar v-if="avatarUrl!==null" :size="50" :src="avatarUrl"></el-avatar>
        <el-avatar v-else :size="50" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
        <span class="userName">{{name}}</span>
    </div>
</template>

<script>
export default {
    name: "UserBrief",
    props: [ "avatarUrl", "name" ],
}
</script>
