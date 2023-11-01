import { atom } from 'recoil';

export const selectedMenuState = atom({
  key: 'selectedMenu',
  default: '',
});

export const DeviceState = atom({
  key: 'device',
  default: '',
});

export const PartState = atom({
  key: 'part',
  default: '',
});
