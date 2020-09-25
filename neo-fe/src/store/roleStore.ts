import { action, observable, runInAction } from 'mobx'
import { fetchInfo } from '@/apis/auth'
import { handleError } from '@/libs/axios'

class Role {
  @observable currentRole!: any

  @action
  fetchCurrentRole = async () => {
    try {
      const { data } = await fetchInfo()
      runInAction(() => {
        this.currentRole = data
      })
    } catch (e) {
      handleError(e)
      window.location.href = '/login'
    }
  }
}
export const RoleStore = new Role()
