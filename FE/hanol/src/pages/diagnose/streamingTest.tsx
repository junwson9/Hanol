import React, { useRef } from 'react';

const CameraApp: React.FC = () => {
  const fileInputRef = useRef<HTMLInputElement | null>(null);

  const handleFileChange = async (event: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = event.target.files?.[0];

    if (selectedFile) {
      // 여기서 서버로 파일을 업로드하거나 다른 작업을 수행할 수 있습니다.
      console.log('Selected File:', selectedFile);

      // Optional: Display the selected image
      const reader = new FileReader();
      reader.onload = () => {
        const imageUrl = reader.result as string;
        // 여기서 imageUrl을 상태에 저장하거나 사용할 수 있습니다.
        console.log('Image URL:', imageUrl);
      };
      reader.readAsDataURL(selectedFile);
    }
  };

  return (
    <div>
      <input
        type="file"
        accept="image/*"
        capture="environment"
        ref={fileInputRef}
        onChange={handleFileChange}
        style={{ display: 'none' }}
      />

      <button onClick={() => fileInputRef.current?.click()}>Upload Photo</button>
    </div>
  );
};

export default CameraApp;
