const LoadingSpinner = () => {
  return (
    <div className="w-full bg-surface opacity-25 h-screen z-50 flex items-center justify-center">
      <div className="mx-auto h-30 w-30 border-8 border-primary border-b-primary-light rounded-full animate-spin"></div>
    </div>
  );
};

export default LoadingSpinner;
