import { action, observable, runInAction } from 'mobx'
import { fetchInfo } from '@/apis/auth'
import { handleError } from '@/libs/axios'
// import { IRoles, IConfig, ICurrentAuth } from '@/interface/auth'
// import { getListRoles, getConfigList, getCurrentAdmin } from '@/api/auth'
// import localStorage from '@/lib/local-storage'

class Role {
  // @observable loading = true
  // @observable roleList: Array<IRoles> = []
  // @observable configList: Array<IConfig> = []
  @observable currentRole!: any
  // @action
  // fetchRoleList = async () => {
  //   try {
  //     this.loading = true
  //     const { data } = await getListRoles()
  //     runInAction(() => {
  //       this.roleList = data.filter((item) => item.status === 'ENABLED')
  //       this.loading = false
  //     })
  //   } catch (e) {
  //     runInAction(() => {
  //       this.loading = false
  //     })
  //     console.error(e)
  //   }
  // }
  // @action
  // fetchConfigList = async () => {
  //   try {
  //     this.loading = true
  //     const { data } = await getConfigList()
  //     runInAction(() => {
  //       this.configList = data.filter((it) => it.status === 'ENABLED')
  //       this.loading = false
  //     })
  //   } catch (e) {
  //     runInAction(() => {
  //       this.loading = false
  //     })
  //     console.error(e)
  //   }
  // }
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
  // @computed
  // get fetchUserCode() {
  //   return toJS(this.currentRole?.configurations.map((item: { code: string }) => item.code))
  // }
}
export const RoleStore = new Role()
