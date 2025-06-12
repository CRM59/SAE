<template>
  <Transition mode="in-out" appear>
    <table ref="child" v-if="licensees !== null && licensees.length !== 0">
      <thead>
      <tr>
        <th>{{$t('labels.lastName')}}</th>
        <th>{{$t('labels.firstName')}}</th>
        <th>{{$t('labels.sport')}}</th>
        <th>{{$t('labels.licenseesStatus')}}</th>
        <th>{{$t('labels.endDate')}}</th>
      </tr>
      </thead>
      <tbody>
      <TransitionGroup name="tableTransition" appear>
        <tr v-for="license in licensees" :key="license.person.lastName + license.person.firstName">
          <td>{{license.person.lastName}}</td>
          <td>{{license.person.firstName}}</td>
          <td>{{license.sport}}</td>
          <td>
            <img class="statut-icon" v-if="statut === 'ACTIF'" src="../../assets/img/check.png">
            <img class="statut-icon" v-if="statut === 'INACTIF'" src="../../assets/img/cross.png">
          </td>
          <td>{{license.endDate}}</td>
          <button class="update-button" @click="$router.push(`/LicenseesForm?lastName=${license.person.lastName}&firstName=${license.person.firstName}&sportName=${license.sport}`)" role="link"><img src="../../assets/img/update.png" class="update-icon"></button>
        </tr>
      </TransitionGroup>
      </tbody>
    </table>
  </Transition>
</template>

<script setup>

defineProps({
  licensees: Object,
  statut: String
})

</script>

<style scoped>
.v-enter-active,
.v-leave-active {
  transition: 1s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
  margin-top: 100px;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
  margin-top: 100px;
}

.tableTransition-enter-active{
  transition: 1s ease;
}

.tableTransition-enter-from,
.tableTransitionv-leave-to {
  opacity: 0;
  margin-top: 100px;
}

.tableTransition-enter-from,
.tableTransition-leave-to {
  opacity: 0;
  margin-top: 100px;
}

</style>