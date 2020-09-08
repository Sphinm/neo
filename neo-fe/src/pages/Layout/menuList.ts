// import { lazy } from 'react'
import createLazy from '@/libs/lazy'
import {
  ApartmentOutlined,
  AuditOutlined,
  BarsOutlined,
  CheckCircleOutlined,
  HomeOutlined,
  LogoutOutlined,
  MoneyCollectOutlined,
  SettingFilled,
  SettingOutlined,
  ShakeOutlined,
  TrademarkCircleOutlined,
  UnorderedListOutlined,
  UsergroupAddOutlined,
  UserSwitchOutlined,
  ToolOutlined,
  FileImageOutlined,
} from '@ant-design/icons'

export interface ISideBarList {
  name: string
  path?: string
  icon: typeof HomeOutlined
  children?: menuListType
  isHidden?: boolean
  permissions?: string[]
  component?: React.ComponentType<any>
}
// TODO 接口调整
export type menuListType = Array<ISideBarList>

// SideBar Config
export const SideBarList: menuListType = [
  // {
  //   name: 'Approval Center',
  //   path: '/approval-center',
  //   icon: CheckCircleOutlined,
  //   permissions: ['APPROVAL_CENTER', 'APPLICATION', 'RISK_CONFIG'],
  //   // permissions: ['APPROVAL_CENTER', 'STATISTICS', 'APPLICATION'],
  //   children: [
  //     {
  //       name: 'Application',
  //       path: '/approval-center/application',
  //       icon: AuditOutlined,
  //       children: [],
  //       permissions: ['APPLICATION'],
  //       component: createLazy(() => import('@/views/ApprovalCenter/Application')),
  //     },
  //     {
  //       name: 'ApplicationDetail',
  //       path: '/approval-center/application-detail/:applicationRiskId',
  //       icon: AuditOutlined,
  //       isHidden: true,
  //       children: [],
  //       permissions: ['APPLICATION'],
  //       component: createLazy(() => import('@/views/ApprovalCenter/ApplicationDetail')),
  //     },
  //     {
  //       name: 'Config',
  //       path: '/approval-center/config',
  //       icon: SettingOutlined,
  //       children: [],
  //       permissions: ['RISK_CONFIG'],
  //       component: createLazy(() => import('@/views/ApprovalCenter/Config')),
  //     },
  //   ],
  // },
  // {
  //   name: 'Financial Center',
  //   path: '/financial-center',
  //   icon: MoneyCollectOutlined,
  //   permissions: ['FINANCE_BILL', 'FINANCE_BANK_RECORD', 'FINANCE_OFFLINE_APPROVE'],
  //   children: [
  //     {
  //       name: 'Bill List',
  //       path: '/financial-center/bill-list',
  //       icon: UnorderedListOutlined,
  //       children: [],
  //       permissions: ['FINANCE_BILL'],
  //       component: createLazy(() => import('@/views/Finance/BillList')),
  //     },
  //     {
  //       name: 'Bank Record',
  //       path: '/financial-center/bank-record',
  //       icon: TrademarkCircleOutlined,
  //       children: [],
  //       permissions: ['FINANCE_BANK_RECORD'],
  //       component: createLazy(() => import('@/views/Finance/BankRecord')),
  //     },
  //     {
  //       name: 'Repay Application',
  //       path: '/financial-center/repay-application',
  //       icon: UserSwitchOutlined,
  //       children: [],
  //       permissions: ['FINANCE_OFFLINE_APPROVE'],
  //       component: createLazy(() => import('@/views/Finance/OfflineApprove')),
  //     },
  //     {
  //       name: 'Overpai Order',
  //       path: '/financial-center/overpai-order',
  //       icon: LogoutOutlined,
  //       children: [],
  //       permissions: ['OVERPAI_ORDER'],
  //       component: createLazy(() => import('@/views/Finance/Overpaid')),
  //     },
  //   ],
  // },
  // {
  //   name: 'Coupon Management',
  //   path: '/coupon-management',
  //   icon: ShakeOutlined,
  //   permissions: ['COUPON_MANAGEMENT', 'COUPON_LIST'],
  //   children: [
  //     {
  //       name: 'Coupon List',
  //       path: '/coupon-management/coupon-list',
  //       icon: BarsOutlined,
  //       children: [],
  //       permissions: ['COUPON_LIST'],
  //       component: createLazy(() => import('@/views/Coupon')),
  //     },
  //   ],
  // },
  // {
  //   name: 'Settlement System',
  //   path: '/settlement-systemt',
  //   icon: ShakeOutlined,
  //   permissions: ['SETTLEMENT_ORDER', 'SETTLEMENT_APPROVE'],
  //   children: [
  //     {
  //       name: 'Order',
  //       path: '/settlement-systemt/order',
  //       icon: BarsOutlined,
  //       children: [],
  //       permissions: ['SETTLEMENT_ORDER'],
  //       component: createLazy(() => import('@/views/SettlementSystem/Order')),
  //     },
  //     {
  //       name: 'Approve',
  //       path: '/settlement-systemt/approve',
  //       icon: BarsOutlined,
  //       children: [],
  //       permissions: ['SETTLEMENT_APPROVE'],
  //       component: createLazy(() => import('@/views/SettlementSystem/Approve')),
  //     },
  //   ],
  // },
  // {
  //   name: 'Operation Tools',
  //   path: '/operation-tools',
  //   icon: ToolOutlined,
  //   permissions: ['OPERATION_TOOLS', 'BANNERS_ICONS'],
  //   children: [
  //     {
  //       name: 'Banners & Icons',
  //       path: '/operation-tools/banners-icons',
  //       icon: FileImageOutlined,
  //       children: [],
  //       permissions: ['BANNERS_ICONS'],
  //       component: createLazy(() => import('@/pages/OperationTools')),
  //     },
  //   ],
  // },
  // {
  //   name: 'Setting',
  //   icon: SettingFilled,
  //   path: '/setting',
  //   permissions: ['SETTING_ROLE', 'SETTING_USER'],
  //   children: [
  //     {
  //       name: 'Role Management',
  //       path: '/role-management',
  //       icon: ApartmentOutlined,
  //       children: [],
  //       permissions: ['SETTING_ROLE'],
  //       component: createLazy(() => import('@/views/Setting/RoleManagement')),
  //     },
  //     {
  //       name: 'User Management',
  //       path: '/user-management',
  //       icon: UsergroupAddOutlined,
  //       children: [],
  //       permissions: ['SETTING_USER'],
  //       component: createLazy(() => import('@/views/Setting/UserManagement')),
  //     },
  //   ],
  // },
]
