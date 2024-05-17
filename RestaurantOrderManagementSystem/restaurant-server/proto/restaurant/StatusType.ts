// Original file: restaurant.proto

export const StatusType = {
  ORDERED: 0,
  IN_PROGRESS: 1,
  READY: 2,
  COMPLETED: 3,
} as const;

export type StatusType =
  | 'ORDERED'
  | 0
  | 'IN_PROGRESS'
  | 1
  | 'READY'
  | 2
  | 'COMPLETED'
  | 3

export type StatusType__Output = typeof StatusType[keyof typeof StatusType]
