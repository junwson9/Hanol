import { atom } from 'recoil';

export const selectedMenuState = atom({
  key: 'selectedMenu',
  default: '',
});

export const DeviceState = atom({
  key: 'device',
  default: 0,
});

export const PartState = atom({
  key: 'part',
  default: '',
});

export const GenderInfo = atom<string>({
  key: 'Gender',
  default: '',
});

export const BirthInfo = atom<string>({
  key: 'Birth',
  default: '',
});

export const ImageState = atom<string>({
  key: 'Image',
  default: '',
});
