export interface diagnosisResultType {
  diagnosis_id: number;
  member_id: number;
  value1: number;
  value2: number;
  value3: number;
  value4: number;
  value5: number;
  value6: number;
  image_url: string;
  device_type: number;
  scan_part: number;
  created_date: string;
}

export interface aboutnotiSettingType {
  notification_setting_id: number;
  member_id: number;
  is_check_routine_active: boolean;
  is_individual_routine_active: boolean;
}

export interface datelistType {
  diagnosis_id: number;
  created_date: string;
}
