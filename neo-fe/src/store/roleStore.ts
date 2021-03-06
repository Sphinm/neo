import { action, observable, runInAction } from 'mobx'
import { fetchInfo } from '@/apis/auth'
import { handleError } from '@/libs/axios'

class Role {
  @observable currentRole!: any

  @action
  fetchCurrentRole = async () => {
    try {
      const { data, code } = await fetchInfo()
      if (code === 'SUCCESS' && data) {
        runInAction(() => {
          this.currentRole = data
        })
        localStorage.setItem('userInfo', JSON.stringify(data))
      } else {
        window.location.href = '/login'
      }
    } catch (e) {
      handleError(e)
      window.location.href = '/login'
    }
  }
}
export const RoleStore = new Role()
