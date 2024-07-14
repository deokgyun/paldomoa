import Footer from "./component/Footer/Footer.tsx";
import Header from "./component/layout/Header.tsx";

function App() {

    const isLoggedIn = false; // 로그인 상태를 관리하는 로직을 추가하세요.

    return (
        <div className="flex flex-col min-h-screen">
            <Header isLoggedIn={isLoggedIn} />
            <main className="flex-grow">

            </main>
            <Footer />
        </div>
    );
};

export default App
